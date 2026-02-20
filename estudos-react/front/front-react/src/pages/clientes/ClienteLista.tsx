import { Button, Form, InputGroup } from 'react-bootstrap'
import TitlePage from '../../components/TitlePage'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const clientes = [
    {
        id: 1,
        nome: 'Felipão',
        endereco: 'Rua Paraíba, 609',
        contato: '46999040238',
        situacao: 'Ativo',
    },
    {
        id: 2,
        nome: 'Rubão do pontaço',
        endereco: 'Rua Paraíba, 609',
        contato: '46999078238',
        situacao: 'Ativo',
    },
    {
        id: 3,
        nome: 'Veio da Havan',
        endereco: 'rua de londres, 17',
        contato: '46999000238',
        situacao: 'Ativo',
    },
    {
        id: 4,
        nome: 'Luiz Inacio da Silva',
        endereco: 'Rua das ruas, 13',
        contato: '46999040298',
        situacao: 'Ativo',
    },

]

const ClienteLista = () => {

    const navigate = useNavigate();
    const [termoBusca, setTermoBusca] = useState('');

    const handleImputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setTermoBusca(e.target.value);
    };

    const clientesFiltrados = clientes.filter((cliente) => {
        return Object.values(cliente).join(' ').toLowerCase().includes(termoBusca.toLowerCase());
    });

    const novoCliente = () => {
        navigate('/cliente/detalhe');
    }

    return (
        <>
            <TitlePage tituloPagina='Clientes:'>
                <Button variant='outline-secondery' onClick={novoCliente}>
                    <i className="fas fa-plus me-2"></i>
                    Novo Cliente
                </Button>
            </TitlePage>

            <InputGroup className="mb-3 mt-3">
                <InputGroup.Text id="inputGroup-sizing-default">
                    Buscar:
                </InputGroup.Text>
                <Form.Control onChange={handleImputChange} placeholder='Buscar por nome do cliente' />
            </InputGroup>

            <table className="table table-striped table-hover">
                <thead className="table-dark mt-3">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Endereço</th>
                        <th scope="col">Contato</th>
                        <th scope="col">Situação</th>
                        <th scope="col">Opções</th>
                    </tr>
                </thead>
                <tbody>
                    {clientesFiltrados.map((cliente) => (
                        <tr key={cliente.id}>
                            <td>{cliente.id}</td>
                            <td>{cliente.nome}</td>
                            <td>{cliente.endereco}</td>
                            <td>{cliente.contato}</td>
                            <td>{cliente.situacao}</td>
                            <td>
                                <div>
                                    <button 
                                        className="btn btn-sm btn-outline-primary me-2" 
                                        onClick={() => navigate(`/cliente/detalhe/${cliente.id}`)}>
                                        <i className='fas fa-user-edit me-2'></i> Editar
                                    </button>
                                    <button className="btn btn-sm btn-outline-danger me-2">
                                        <i className='fas fa-user-times me-2'></i> Desativar
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}

                </tbody>
            </table>
        </>
    )
}

export default ClienteLista;
