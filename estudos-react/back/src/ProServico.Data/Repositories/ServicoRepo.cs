using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ProServico.Data.Context;
using ProServico.Domain.Entities;
using ProServico.Domain.Interfaces.Repositories;

namespace ProServico.Data.Repositories
{
    public class ServicoRepo : GeralRepo, IServicoRepo
    {
        private readonly DataContext _context;
        public ServicoRepo(DataContext context) : base(context)
        {
            _context = context;

        }

        public async Task<Servico> PegaPorIdAsync(int id)
        {
            IQueryable<Servico> query = _context.Servicos;

            query = query.AsNoTracking().OrderBy(serv => serv.Id).Where(s => s.Id == id);

            return await query.FirstOrDefaultAsync();
        }

        public async Task<Servico> PegaPorTituloAsync(string titulo)
        {
            IQueryable<Servico> query = _context.Servicos;

            query = query.AsNoTracking().OrderBy(serv => serv.Id);

            return await query.FirstOrDefaultAsync(s => s.Titulo == titulo);
        }

        public async Task<Servico[]> PegaTodosAsync()
        {
            IQueryable<Servico> query = _context.Servicos;

            query = query.AsNoTracking().OrderBy(serv => serv.Id);

            return await query.ToArrayAsync();
        }
    }
}