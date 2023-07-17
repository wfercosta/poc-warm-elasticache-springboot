resource "aws_sqs_queue" "subscription" {
  name = "poc-elasticache-sqs-sns-subscription"
}

resource "aws_sqs_queue_policy" "subscription" {
  queue_url = aws_sqs_queue.subscription.id

  policy = templatefile("_templates/resource-policy-sqs.tfpl", {
    service    = "sns.amazonaws.com"
    sqs_arn    = aws_sqs_queue.subscription.arn
    source_arn = aws_sns_topic.this.arn
  })
}

resource "aws_sns_topic_subscription" "subscription" {
  topic_arn = aws_sns_topic.this.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.subscription.arn
}

module "lambda" {
  source             = "./_modules/aws-lambda"
  function_name      = "fn-transform-sns-to-task"
  function_handler   = "function.handler.handle"
  runtime            = "python3.9"
  execution_role_arn = "arn:aws:iam::${local.account_id}:role/iamsr/lambda-function-role"
  function_source    = "../app/functions/fn-transform-sns-to-task/bin/function.zip"
}

resource "aws_sqs_queue" "dest" {
  name = "poc-elasticache-sqs-destination"
}

resource "aws_sqs_queue_policy" "dest" {
  queue_url = aws_sqs_queue.dest.id

  policy = templatefile("_templates/resource-policy-sqs.tfpl", {
    service    = "lambda.amazonaws.com"
    sqs_arn    = aws_sqs_queue.dest.arn
    source_arn = module.lambda.arn
  })
}

resource "aws_lambda_event_source_mapping" "sqs" {
  event_source_arn = aws_sqs_queue.subscription.arn
  enabled          = true
  function_name    = module.lambda.arn
  batch_size       = 1
}


# resource "aws_lambda_function_event_invoke_config" "dest_success" {
#   function_name = module.lambda.function_name

#   destination_config {
#     on_success {
#       destination = aws_sqs_queue.dest.arn
#     }
#   }
# }
