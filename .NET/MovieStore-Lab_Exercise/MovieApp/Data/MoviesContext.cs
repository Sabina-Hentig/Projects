using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using MovieApp.Model;


namespace MovieApp.Data
{
    public class MoviesContext : DbContext
    {
        public MoviesContext(DbContextOptions<MoviesContext> options) : base(options) { }

        public DbSet<Movie> Movies { get; set; }
    }
}
