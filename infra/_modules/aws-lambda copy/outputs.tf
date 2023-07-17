
output "arn" {
  value = aws_lambda_function.this.arn
}

output "qualified_arn" {
  value = aws_lambda_function.this.qualified_arn
}

output "function_name" {
  value = aws_lambda_function.this.function_name
}
