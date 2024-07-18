using RabbitMQ.Client.Events;
using RabbitMQ.Client;
using System.Text;

namespace MovieApp.RabbitReceiver
{
    public class ClientRabbitMQReceiver
    {
        private readonly IConnection _connection;

        public ClientRabbitMQReceiver()
        {
            var factory = new ConnectionFactory() { HostName = "localhost" };
            _connection = factory.CreateConnection();
        }

        public void StartListening()
        {
            using (var channel = _connection.CreateModel())
            {
                channel.QueueDeclare(queue: "messages",
                                     durable: false,
                                     exclusive: false,
                                     autoDelete: false,
                                     arguments: null);

                var consumer = new EventingBasicConsumer(channel);
                consumer.Received += (model, ea) =>
                {
                    var body = ea.Body.ToArray();
                    var message = Encoding.UTF8.GetString(body);
                    Console.WriteLine(" [x] Received {0}", message);
                };
                channel.BasicConsume(queue: "messages",
                                     autoAck: true,
                                     consumer: consumer);

                Console.WriteLine(" Press [enter] to exit.");
                Console.ReadLine();
            }
        }
    }
}
