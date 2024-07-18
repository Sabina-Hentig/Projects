using Microsoft.AspNetCore.Mvc;
using MovieApp.Model;
using MovieApp.RabbitSender;
using MovieApp.Service;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace MoviesApp.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MoviesController : ControllerBase
    {
        private readonly IMovieService _movieService;

        public MoviesController(IMovieService movieService)
        {
            _movieService = movieService;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Movie>>> GetMovies()
        {
            return Ok(await _movieService.GetMovies());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Movie>> GetMovie(int id)
        {
            var movie = await _movieService.GetMovieById(id);

            if (movie == null)
            {
                return NotFound();
            }

            return Ok(movie);
        }

        [HttpPost]
        public async Task<ActionResult<Movie>> PostMovie(Movie movie)
        {
            await _movieService.AddMovie(movie);

            var rabbitMQ = new RabbitMQSender();
            rabbitMQ.SendMessage($"New movie added: Title: {movie.Title}, Price: {movie.Price}");

            return CreatedAtAction(nameof(GetMovie), new { id = movie.Id }, movie);
        }
    }
}


