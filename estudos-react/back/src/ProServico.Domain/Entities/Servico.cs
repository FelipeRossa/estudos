
using System;

namespace ProServico.Domain.Entities
{
    public class Servico
    {

        public int Id { get; set; }

        public string Titulo { get; set; }

        public string Descricao { get; set; }

        public DateTime DataCriacao { get; set; }

        public DateTime? DataConclusao { get; set; }

        public Prioridade Prioridade { get; set; }

        public Servico() {
            DataCriacao = DateTime.Now;
            DataConclusao = null;
        }

        public Servico(int id, string titulo, string descricao) : this()
        {
            Id = id;
            Titulo = titulo;
            Descricao = descricao;
        }

        public void Concluir() {
            if (DataConclusao == null) {
                DataConclusao = DateTime.Now;
            } else {
                throw new Exception($"Serviço já concluído em{DataConclusao?.ToString("dd/MM/yyyy hh:mm")}");
            }
        }
    }
}