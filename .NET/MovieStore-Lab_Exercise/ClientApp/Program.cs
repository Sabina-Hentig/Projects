using Microsoft.EntityFrameworkCore;
using ClientApp.Data;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();



// Register DbContext for Clients
builder.Services.AddDbContext<ClientsContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("ClientsContext")));


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

// Start RabbitMQ receiver for Clients


app.Run();
