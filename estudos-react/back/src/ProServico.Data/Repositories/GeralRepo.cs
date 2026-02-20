using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ProServico.Data.Context;
using ProServico.Domain.Interfaces.Repositories;

namespace ProServico.Data.Repositories
{
    public class GeralRepo : IGeralRepo
    {
        private readonly DataContext _context;
        public GeralRepo(DataContext context)
        {
            _context = context;

        }

        public void Adicionar<T>(T entity) where T : class
        {
            _context.Add(entity);
        }

        public void Atualizar<T>(T entity) where T : class
        {
            _context.Update(entity);
        }

        public void Deletar<T>(T entity) where T : class
        {
            _context.Remove(entity);
        }

        public void Deletarvarios<T>(T[] entityArr) where T : class
        {
            _context.RemoveRange(entityArr);
        }

        public async Task<bool> SalvarMudancasAsync()
        {
            return (await _context.SaveChangesAsync()) > 0;
        }
    }
}