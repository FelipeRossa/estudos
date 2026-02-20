import { Prioridade } from "../../model/servico";
import { ServicoItemProps } from "../../model/servicoProps";

const ServicoItem: React.FC<ServicoItemProps> = ({servico, getServico, handleConfirmModal}: ServicoItemProps) => {
        
    function prioridadeLabel(param: string) {
        switch (param) {
            case Prioridade.Alta:
            case Prioridade.Normal:
            case Prioridade.Baixa:
                return param;
            default:
                return 'Não definido';
        }
    }

    function prioridadeSyle(param: string, icone: Boolean) {
        switch (param) {
            case Prioridade.Alta:
                return icone ? 'frown' : 'warning';
            case Prioridade.Normal:
                return icone ? 'meh' : 'black';
            case Prioridade.Baixa:
                return icone ? 'smile' : 'success';
            default:
                return 'Não definido';
        }
    }

    return (
        <div key={servico.id} className={"card mb-2 shadow border border-" + prioridadeSyle(servico.prioridade, false)} >
            <div className="card-body">
                <div className="d-flex justify-content-between">
                    <h5 className="card-title">
                        <span className="badge rounded-pill bg-secondary me-1">{servico.id}</span>
                        - {servico.titulo}
                    </h5>

                    <h6 className="d-flex justify-content-between">
                        Prioridade:
                        <span className={'ms-1 text-' + prioridadeSyle(servico.prioridade, false)}>
                            <i className={"me-1 far fa-face-" + prioridadeSyle(servico.prioridade, true)}></i>
                            {prioridadeLabel(servico.prioridade)}
                        </span>

                    </h6>
                </div>
                <p className="card-text">{servico.descricao}</p>
                <div className="d-flex justify-content-end border-top pt-2 m-0">
                    <button className="btn btn-outline-primary me-2 btn-sm"
                        onClick={() => getServico(servico.id)}>
                        <i className="fas fa-pen me-2"></i>
                        Editar
                    </button>
                    <button
                        className="btn btn-outline-danger btn-sm"
                        onClick={() => handleConfirmModal(servico.id)}>
                        <i className="fas fa-trash me-2"></i>
                        Deletar
                    </button>
                </div>
            </div>
        </div>

    )
}

export default ServicoItem;