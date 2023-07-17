
resource "aws_security_group" "this" {
  name   = "poc-elasticache"
  vpc_id = data.aws_vpc.this.id

  ingress {
    description = "Redis"
    from_port   = 6379
    to_port     = 6379
    protocol    = "tcp"
    cidr_blocks = [data.aws_vpc.this.cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

}

resource "aws_sns_topic" "this" {
  name = "poc-elasticache-sns"
}

resource "aws_elasticache_subnet_group" "this" {
  name       = "poc-elasticache-subnet-group"
  subnet_ids = toset(data.aws_subnets.this.ids)
}

resource "aws_elasticache_replication_group" "this" {
  automatic_failover_enabled = true
  replication_group_id       = "poc-elasticache-replication-group"
  description                = "Elasticache used for poc spring boot warm up"
  node_type                  = "cache.t2.micro"
  parameter_group_name       = "default.redis7.cluster.on"
  port                       = 6379
  security_group_ids         = [aws_security_group.this.id]
  subnet_group_name          = aws_elasticache_subnet_group.this.name
  num_cache_clusters         = 2
  notification_topic_arn     = aws_sns_topic.this.arn
}

