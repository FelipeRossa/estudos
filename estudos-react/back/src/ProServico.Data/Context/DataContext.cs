using Microsoft.EntityFrameworkCore;
using ProServico.Data.Mappings;
using ProServico.Domain.Entities;

namespace ProServico.Data.Context
{
    public class DataContext : DbContext
    {

        public DataContext(DbContextOptions<DataContext> options) : base(options) { }

        public DbSet<Servico> Servicos { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new ServicoMap());
        }
    }
}