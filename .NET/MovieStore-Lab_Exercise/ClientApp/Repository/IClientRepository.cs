using ClientApp.Models;
using System.Threading.Tasks;

namespace ClientApp.Repository
{
    public interface IClientRepository
    {
        Task<IEnumerable<Client>> GetClientsAsync();
        Task<Client> GetClientByIdAsync(int id);
        Task AddClientAsync(Client client);
        Task SaveAsync();
    }
}
