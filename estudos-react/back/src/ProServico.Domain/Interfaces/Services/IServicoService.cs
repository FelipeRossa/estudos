using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Threading.Tasks.Dataflow;
using ProServico.Domain.Entities;

namespace ProServico.Domain.Interfaces.Services
{
    public interface IServicoService
    {

        Task<Servico> AdicionarServico(Servico model);
        
        Task<Servico> AtualizarServico(Servico model);

        Task<bool> DeletarServico(int servicoId);

        Task<bool> ConcluirServico(Servico model);

        Task<Servico[]> PegarTodosServivoAsync();

        Task<Servico> PegarServivoPorIdasync(int servicoId);
    }
}