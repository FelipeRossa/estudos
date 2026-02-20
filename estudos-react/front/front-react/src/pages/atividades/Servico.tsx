import React, { useEffect, useState } from 'react';

import ServicoLista from './ServicoLista';
import api from '../../api/servico';
import { Button, Modal } from 'react-bootstrap';
import TitlePage from '../../components/TitlePage';
import ServicoForm from './ServicoForm';
import { IServico, Prioridade } from '../../model/servico';

const servicoInicial: IServico = {
  id: 0,
  prioridade: Prioridade.NaoDefinido,
  descricao: '',
  titulo: ''
}

const Servico: React.FC = () => {
  const [showServModal, setShowServModal] = useState(false);
  const [smShowConfirmModal, setSmShowConfirmModal] = useState(false);

  const [servicos, setServicos] = useState<IServico[]>([]);
  const [servico, setServico] = useState<IServico>(servicoInicial);

  const handleServModal = () => setShowServModal(!showServModal);

  const handleConfirmModal = (id: number) => {
    if (id !== 0 && id !== undefined) {
      const servicoFiltrado = servicos.filter(serv => serv.id === id);
      setServico(servicoFiltrado[0]);

    } else {
      setServico(servicoInicial);
    }
    setSmShowConfirmModal(!smShowConfirmModal)
  };

  const pegarTodosServicos = async () => {
    const response = await api.get('Servico');

    return response.data;
  }

  const novoServico = () => {
    setServico(servicoInicial)
    handleServModal()
  }

  useEffect(() => {
    const getServicos = async () => {
      const todosServicos = await pegarTodosServicos();
      if (todosServicos)
        setServicos(todosServicos);
    }

    getServicos();
  }, [])

  const addServico = async (serv: IServico) => {
    const response = await api.post('servico', serv);
    setServicos([...servicos, response.data])
    handleServModal()
  }

  const deletarservico = async (id: number) => {
    handleConfirmModal(0);
    if (await api.delete(`servico/${id}`)) {
      const servicosFiltrados = servicos.filter(serv => serv.id != id);

      setServicos([...servicosFiltrados]);
    }

  }

  function getServico(id: number) {
    const servicoFiltrado = servicos.filter(serv => serv.id === id);
    setServico(servicoFiltrado[0]);
    handleServModal();
  }

  function maxValueServicos() {
    return Math.max.apply(Math, servicos.map(item => item.id)) + 1;
  }

  function cancelarServico() {
    setServico(servicoInicial)
    handleServModal();
  }

  const atualizarServico = async (servicoEdicao: IServico) => {
    const response = await api.put(`servico/${servicoEdicao.id}`, servicoEdicao);
    const { id } = response.data;
    setServicos(servicos.map(item => item.id == id ? response.data : item))

    setServico(servicoInicial)
    handleServModal()
  }

  return (
    <>
      <TitlePage
        tituloPagina = {'Serviço: ' + (servico.id != 0 ? servico.id : '')}
      >
        <Button variant="outline-secondery" onClick={novoServico}>
                <i className='fas fa-plus'></i>
        </Button>
      </TitlePage>

      <ServicoLista
        servicos={servicos}
        getServico={getServico}
        handleConfirmModal={handleConfirmModal}
      />

      <Modal show={showServModal} onHide={handleServModal}>
        <Modal.Header closeButton>
          <Modal.Title>Serviço {servico.id != 0 ? servico.id : ''}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ServicoForm
            addServico={addServico}
            cancelarServico={cancelarServico}
            servicoSelecionado={servico}
            atualizarServico={atualizarServico}
          />
        </Modal.Body>
      </Modal>

      <Modal size='sm'
        show={smShowConfirmModal}
      >
        <Modal.Header closeButton>
          <Modal.Title>Excluindo servico{' '}  {servico.id != 0 ? servico.id : ''}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Tem certeza que deseja excluir o servico {servico.id}
        </Modal.Body>
        <Modal.Footer className='d-flex justfy-content-between'>
          <button className="btn btn-outline-success me-2" onClick={() => deletarservico(servico.id)}>
            <i className='fas fa-check me-2'></i>
            Sim
          </button>

          <button className="btn btn-danger me-2" onClick={() => handleConfirmModal(0)}>
            <i className='fas fa-check me-2'></i>
            Não
          </button>
        </Modal.Footer>
      </Modal>
    </>

  )
}
export default Servico;

