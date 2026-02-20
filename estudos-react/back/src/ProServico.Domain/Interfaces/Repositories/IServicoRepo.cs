using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ProServico.Domain.Entities;

namespace ProServico.Domain.Interfaces.Repositories
{
    public interface IServicoRepo : IGeralRepo
    {
        Task<Servico[]> PegaTodosAsync();
        Task<Servico> PegaPorIdAsync(int id);
        Task<Servico> PegaPorTituloAsync(string titulo);

    }
}