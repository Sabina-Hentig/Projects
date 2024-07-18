using Microsoft.AspNetCore.Connections;
using RabbitMQ.Client;
using System.Text;

namespace ClientApp.RabbitSender
{
    public class RabbitMQSender
    {
        private readonly IConnection _connection;

        public RabbitMQSender()
        {
            var factory = new ConnectionFactory() { HostName = "localhost" };
            _connection = factory.CreateConnection();
        }

        public void SendMessage(string message)
        {
            using (var channel = _connection.CreateModel())
            {
                channel.QueueDeclare(queue: "messages",
                                     durable: false,
                                     exclusive: false,
                                     autoDelete: false,
                                     arguments: null);

                var body = Encoding.UTF8.GetBytes(message);

                channel.BasicPublish(exchange: "",
                                     routingKey: "messages",
                                     basicProperties: null,
                                     body: body);
            }
        }
    }
}
