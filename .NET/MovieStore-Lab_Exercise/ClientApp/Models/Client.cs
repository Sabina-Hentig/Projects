using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ClientApp.Models
{
    public class Client
    {

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        // public int MovieId { get; set; }
        public string Name { get; set; }
        public double TotalCost { get; set; }
    }
}
