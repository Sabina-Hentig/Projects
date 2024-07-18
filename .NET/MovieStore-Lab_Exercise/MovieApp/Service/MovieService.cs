using MovieApp.Model;
using MovieApp.Repository;

namespace MovieApp.Service
{
    public class MovieService : IMovieService
    {
        private readonly IMovieRepository _movieRepository;

        public MovieService(IMovieRepository movieRepository)
        {
            _movieRepository = movieRepository;
        }

        public async Task<IEnumerable<Movie>> GetMovies()
        {
            return await _movieRepository.GetMovies();
        }

        public async Task<Movie> GetMovieById(int id)
        {
            return await _movieRepository.GetMovieById(id);
        }

        public async Task AddMovie(Movie movie)
        {
            await _movieRepository.AddMovie(movie);
            await _movieRepository.SaveChanges();
        }
    }
}
