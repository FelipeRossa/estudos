import { IServico } from "./servico";

export interface ServicoListaProps {
    servicos: IServico[];
    getServico: (id: number) => void;
    handleConfirmModal: (id: number) => void;
}

export interface ServicoItemProps {
    servico: IServico;
    getServico: (id: number) => void;
    handleConfirmModal: (id: number) => void;
};

export interface ServicoFormProps {
    servicoSelecionado: IServico;
    atualizarServico: (servico: IServico) => void;
    addServico: (servico: IServico) => void;
    cancelarServico: () => void;
}