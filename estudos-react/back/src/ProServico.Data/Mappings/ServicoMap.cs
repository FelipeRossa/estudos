using ProServico.Domain.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace ProServico.Data.Mappings
{
    public class ServicoMap : IEntityTypeConfiguration<Servico>
    {
        public void Configure(EntityTypeBuilder<Servico> builder)
        {

            builder.ToTable("Servicos");
            builder.Property(a => a.Titulo).HasColumnType("varchar(100)");
            builder.Property(a => a.Descricao).HasColumnType("varchar(255)");

        }
    }
}