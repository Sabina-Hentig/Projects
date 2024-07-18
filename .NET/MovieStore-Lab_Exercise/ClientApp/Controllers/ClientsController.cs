using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ClientApp.Models;
using ClientApp.RabbitSender;
using ClientApp.Data;

[Route("api/[controller]")]
[ApiController]
public class ClientsController : ControllerBase
{
    private readonly ClientsContext _context;

    public ClientsController(ClientsContext context)
    {
        _context = context;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Client>>> GetClients()
    {
        return await _context.Clients.ToListAsync();
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Client>> GetClient(int id)
    {
        var client = await _context.Clients.FindAsync(id);

        if (client == null)
        {
            return NotFound();
        }

        return client;
    }

    [HttpPost]
    public async Task<ActionResult<Client>> PostClient(Client client)
    {
        _context.Clients.Add(client);
        await _context.SaveChangesAsync();

        var rabbitMQ = new RabbitMQSender();
        rabbitMQ.SendMessage($"New client added: Name: {client.Name}, Total Cost: {client.TotalCost}");

        return CreatedAtAction(nameof(GetClient), new { id = client.Id }, client);
    }
}
