import pika
import sys

credentials = pika.PlainCredentials('alex', 'alex123')
connection = pika.BlockingConnection(pika.ConnectionParameters(
    '192.168.14.52',credentials=credentials))
channel = connection.channel()

channel.exchange_declare(exchange='direct_logs',type='direct')

result = channel.queue_declare(exclusive=True)
queue_name = result.method.queue



severities = sys.argv[1:]
if not severities:
    sys.stderr.write("Usage: %s [info] [warning] [error]\n" % sys.argv[0])
    sys.exit(1)

for severity in severities:
    channel.queue_bind(exchange='direct_logs',queue=queue_name,routing_key=severity)

print(' [*] Waiting for logs. To exit press CTRL+C')


def callback(ch, method, properties, body):
    print(" [x] %r:%r" % (method.routing_key, body))


channel.basic_consume(callback,
                      queue=queue_name,
                      no_ack=True)

channel.start_consuming()