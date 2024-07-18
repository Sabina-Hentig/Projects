using ClientApp.Models;

namespace ClientApp.Service
{
    public interface IClientService
    {
        Task<IEnumerable<Client>> GetAllClientsAsync();
        Task<Client> GetClientAsync(int id);
        Task AddClientAsync(Client client);
    }
}
