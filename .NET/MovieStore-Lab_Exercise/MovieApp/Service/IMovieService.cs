using MovieApp.Model;

namespace MovieApp.Service
{
    public interface IMovieService
    {
        Task<IEnumerable<Movie>> GetMovies();
        Task<Movie> GetMovieById(int id);
        Task AddMovie(Movie movie);
    }
}
