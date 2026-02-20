import { useEffect, useState } from "react";
import { IServico, Prioridade } from "../../model/servico";
import { ServicoFormProps } from "../../model/servicoProps";

const servicoInicial: IServico = {
    id: 0,
    prioridade: Prioridade.NaoDefinido,
    descricao: '',
    titulo: ''
}

const ServicoForm: React.FC<ServicoFormProps> = ({
    servicoSelecionado,
    atualizarServico,
    addServico,
    cancelarServico

}: ServicoFormProps) => {

    const [servico, setServico] = useState<IServico>(servicoAtual());

    useEffect(() => {
        if (servicoSelecionado.id != 0) {
            setServico(servicoSelecionado)
        }
    }, [servicoSelecionado]);

    const handleValue = (e: any) => {
        const { name, value } = e.target;

        setServico({ ...servico, [name]: value })
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (servicoSelecionado.id != 0) {
            atualizarServico(servico);
        } else {
            addServico(servico);
        }

        setServico(servicoInicial);
    }

    function servicoAtual(): IServico {
        if (servicoSelecionado.id != 0) {
            return servicoSelecionado;

        } else {
            return servicoInicial;
        }
    }

    const cancelar = (e: React.FormEvent<HTMLButtonElement>) => {
        e.preventDefault();

        cancelarServico();
        setServico(servicoInicial);
    }

    return (
        <>
            <form className='row g-3' onSubmit={handleSubmit}>
                <div className='col-md-6'>
                    <label className='form-label'>Título</label>
                    <input
                        name='titulo'
                        value={servico.titulo}
                        onChange={handleValue}
                        id='titulo'
                        type="text"
                        className="form-control" />
                </div>

                <div className="col-md-6">
                    <label className="form-label">Prioridade</label>
                    <select
                        name='prioridade'
                        value={servico.prioridade}
                        onChange={handleValue}
                        id="prioridade" className="form-select">
                        <option defaultValue="Não Definifo">Selecionar...</option>
                        <option value="Baixa">Baixa</option>
                        <option value="Normal">Normal</option>
                        <option value="Alta">Alta</option>
                    </select>
                </div>

                <div className='col-md-12'>
                    <label className='form-label'>Descrição</label>
                    <textarea
                        name='descricao'
                        value={servico.descricao}
                        onChange={handleValue}
                        id='descricao'
                        className="form-control" />

                    <hr />
                </div>


                <div className='col-12 mt-0'>
                    {servico.id == 0 ? (
                        <button className='btn btn-outline-success' type="submit"><i className="fas fa-plus me-2"></i>Adicionar serviço</button>
                    ) : (
                        <>
                            <button className='btn btn-outline-success me-2' type="submit" ><i className="fas fa-plus me-2"></i>Salvar</button>
                            <button className='btn btn-outline-warning' onClick={cancelar}><i className="fas fa-plus me-2"></i>Cancelar</button>
                        </>
                    )}

                </div>
            </form>
        </>

    )
}
export default ServicoForm;