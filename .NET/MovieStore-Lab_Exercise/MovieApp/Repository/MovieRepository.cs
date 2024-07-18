using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MovieApp.Data;
using MovieApp.Model;

namespace MovieApp.Repository
{
    public class MovieRepository : IMovieRepository
    {
        private readonly MoviesContext _context;

        public MovieRepository(MoviesContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Movie>> GetMovies()
        {
            return await _context.Movies.ToListAsync();
        }

        public async Task<Movie> GetMovieById(int id)
        {
            return await _context.Movies.FindAsync(id);
        }

        public async Task AddMovie(Movie movie)
        {
            await _context.Movies.AddAsync(movie);
        }

        public async Task SaveChanges()
        {
            await _context.SaveChangesAsync();
        }
    }
}

