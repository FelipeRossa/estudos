using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Reflection.Emit;
using System.Threading.Tasks;
using ProServico.Domain.Entities;
using ProServico.Domain.Interfaces.Repositories;
using ProServico.Domain.Interfaces.Services;

namespace ProServico.Domain.Services
{
    public class ServicoService : IServicoService
    {
        private readonly IServicoRepo _servicoRepo;
        public ServicoService(IServicoRepo servicoRepo)
        {
            _servicoRepo = servicoRepo;

        }

        public async Task<Servico> AdicionarServico(Servico model)
        {
            if (await _servicoRepo.PegaPorTituloAsync(model.Titulo) != null)
                throw new Exception("Já existe Serviço com esse nome");

            if (await _servicoRepo.PegaPorIdAsync(model.Id) == null)
            {
                _servicoRepo.Adicionar(model);
                if (await _servicoRepo.SalvarMudancasAsync())
                    return model;
            }

            return null;

        }

        public async Task<Servico> AtualizarServico(Servico model)
        {
            if (model.DataConclusao != null)
                throw new Exception("Não é possível alterar um serviço já finalizado!");


            if (_servicoRepo.PegaPorIdAsync(model.Id) != null)
            {
                _servicoRepo.Atualizar(model);
                if (await _servicoRepo.SalvarMudancasAsync())
                    return model;
            }

            return null;
        }

        public async Task<bool> ConcluirServico(Servico model)
        {
            if (model != null)
            {
                model.Concluir();
                _servicoRepo.Atualizar<Servico>(model);
                return await _servicoRepo.SalvarMudancasAsync();
            }

            return false;
        }

        public async Task<bool> DeletarServico(int servicoId)
        {
            var servico = await _servicoRepo.PegaPorIdAsync(servicoId);
            if (servico == null)
            {
                throw new Exception("Esse serviço não existe!");
            }

            _servicoRepo.Deletar(servico);
            return await _servicoRepo.SalvarMudancasAsync();


        }

        public async Task<Servico> PegarServivoPorIdasync(int servicoId)
        {
            try
            {
                var servico = await _servicoRepo.PegaPorIdAsync(servicoId);
                if (servico == null) return null;

                return servico;
            }
            catch (System.Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<Servico[]> PegarTodosServivoAsync()
        {
            try
            {
                var servicos = await _servicoRepo.PegaTodosAsync();
                if (servicos == null) return null;

                return servicos;
            }
            catch (System.Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }
    }
}