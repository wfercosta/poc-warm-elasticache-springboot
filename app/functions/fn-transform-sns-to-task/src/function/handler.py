import logging
import json
import boto3

__log = logging.getLogger()

def handle(event, context):
    
    __log.info(json.dumps(event, indent=4))

    sqs = boto3.client('sqs')

    queue_url = 'https://sqs.us-east-1.amazonaws.com/175898361931/poc-elasticache-sqs-destination'

    response = sqs.send_message(
        QueueUrl=queue_url,
        DelaySeconds=10,
        MessageAttributes={},
        MessageBody=json.dumps({
            'task_type': 'WARM_UP_CACHE'
        })
    )

    return {
        'statusCode': 200,
        'body': {
            'task_type': 'WARM_UP_CACHE',
            'message_id': response['MessageId']
        }
    }