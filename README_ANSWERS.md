# collatz
Senior Full Stack Dev Technical Test <br>
_by Martin Scheelke_
---
&nbsp;
&nbsp;

#### Question 2.6 and BONUS Question 1 - Deployment target and scability

Some general notes :)

- Idea is to ensure high availability and low latency - checking cost trade-offs.

- Estimate number of simultaneous connections, and load for each connection.
  Estimate total data storage required.

- AWS Elastic Container Service (ECS) - Deploy microservice in multiple docker containers.
  or AWS Elastic Compute Cloud (EC2)
  Auto scaling.
  
- Scaling up and scaling out.

- Load balancing.

- Storage:
    Amazon RDS PostgreSQL
    Amazon Elastic Block Store - auto scaling
    Database mirroring - less load per collatz.database - easily achieved with low churn data like movies.

- Choose availability zone closest to expected customers for best performance.















