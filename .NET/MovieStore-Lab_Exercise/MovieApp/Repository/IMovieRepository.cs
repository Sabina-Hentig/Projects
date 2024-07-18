using MovieApp.Model;

namespace MovieApp.Repository
{
    public interface IMovieRepository
    {
        Task<IEnumerable<Movie>> GetMovies();
        Task<Movie> GetMovieById(int id);
        Task AddMovie(Movie movie);
        Task SaveChanges();
    }
}
