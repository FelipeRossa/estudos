using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using ProServico.Domain.Entities;
using ProServico.Data.Context;
using ProServico.Domain.Interfaces.Services;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace CursoAspnetCore.API.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ServicoController : ControllerBase
    {
        private readonly IServicoService _servicoService;

        public ServicoController(IServicoService servicoService)
        {
            _servicoService = servicoService;

        }

        [HttpGet]
        public async Task<IActionResult> GetAsync()
        {
            try
            {
                var servicos = await _servicoService.PegarTodosServivoAsync();
                if (servicos == null)
                    return NoContent();

                return Ok(servicos);
            }
            catch (System.Exception ex)
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Erro ao tentar recuperar os serviços. Erro: {ex.Message}");
            }
        }



        [HttpGet("{id}")]
        public async Task<IActionResult> Get(int id)
        {
            try
            {
                var servico = await _servicoService.PegarServivoPorIdasync(id);
                if (servico == null)
                    return NoContent();

                return Ok(servico);
            }
            catch (System.Exception ex)
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Erro ao tentar recuperar o serviço id: ${id}. Erro: {ex.Message}");
            }

        }

        [HttpPost]
        public async Task<IActionResult> Post(Servico model)
        {
            try
            {
                var servico = await _servicoService.AdicionarServico(model);
                if (servico == null) return NoContent();

                return Ok(servico);
            }
            catch (System.Exception ex)
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Erro ao tentar salvar o Serviço. Erro: {ex.Message}");
            }

        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, Servico servico)
        {
            if (servico.Id != id)
            {
                return this.StatusCode(StatusCodes.Status409Conflict,
                $"Você está tentando alterar o serviço errado!");
            }

            try
            {
                servico = await _servicoService.AtualizarServico(servico);
                if (servico == null) return NoContent();

                return Ok(servico);
            }
            catch (System.Exception ex)
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Erro ao tentar atualizar o Serviço. Erro: {ex.Message}");
            }

        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            try
            {
                var servico = await _servicoService.PegarServivoPorIdasync(id);
                if (servico == null)
                    return this.StatusCode(StatusCodes.Status409Conflict,
                    $"Você está tentando deletar um serviço inexistente!");

                if (await _servicoService.DeletarServico(id))
                {
                    return Ok(new { message = "Deletado" });

                }
                else
                {
                    return BadRequest("Ocorreu um erro ao tentar a atividade");
                }

            }
            catch (System.Exception ex)
            {
                return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Erro ao tentar deletar o Serviço. Erro: {ex.Message}");
            }
        }

    }
}