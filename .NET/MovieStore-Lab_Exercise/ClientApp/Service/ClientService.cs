using System.Collections.Generic;
using System.Threading.Tasks;
using ClientApp.Models;
using ClientApp.Repositories;
using ClientApp.Repository;
using ClientApp.Service;
using ClientApp.Service;

namespace ClientApp.Services
{
    public class ClientService : IClientService
    {
        private readonly IClientRepository _clientRepository;

        public ClientService(IClientRepository clientRepository)
        {
            _clientRepository = clientRepository;
        }

        public async Task<IEnumerable<Client>> GetAllClientsAsync()
        {
            return await _clientRepository.GetClientsAsync();
        }

        public async Task<Client> GetClientAsync(int id)
        {
            return await _clientRepository.GetClientByIdAsync(id);
        }

        public async Task AddClientAsync(Client client)
        {
            await _clientRepository.AddClientAsync(client);
            await _clientRepository.SaveAsync();
        }
    }
}
