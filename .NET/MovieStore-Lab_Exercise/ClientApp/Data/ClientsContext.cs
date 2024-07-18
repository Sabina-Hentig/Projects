using ClientApp.Models;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;

namespace ClientApp.Data
{
    public class ClientsContext : DbContext
    {
        public ClientsContext(DbContextOptions<ClientsContext> options) : base(options) { }

        public DbSet<Client> Clients { get; set; }
    }
}
