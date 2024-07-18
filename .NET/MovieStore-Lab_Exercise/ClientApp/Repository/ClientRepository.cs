
using Microsoft.EntityFrameworkCore;
using ClientApp.Models;
using ClientApp.Data;
using ClientApp.Repository;

namespace ClientApp.Repositories
{
    public class ClientRepository : IClientRepository
    {
        private readonly ClientsContext _context;

        public ClientRepository(ClientsContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Client>> GetClientsAsync()
        {
            return await _context.Clients.ToListAsync();
        }

        public async Task<Client> GetClientByIdAsync(int id)
        {
            return await _context.Clients.FindAsync(id);
        }

        public async Task AddClientAsync(Client client)
        {
            _context.Clients.Add(client);
        }

        public async Task SaveAsync()
        {
            await _context.SaveChangesAsync();
        }
    }
}
