using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ProServico.Domain.Interfaces.Repositories
{
    public interface IGeralRepo
    {
        void Adicionar<T>(T entity) where T: class;

        void Atualizar<T>(T entity) where T: class;

        void Deletar<T>(T entity) where T: class;

        void Deletarvarios<T>(T[] entity) where T: class;

        Task<bool> SalvarMudancasAsync();
    }
}