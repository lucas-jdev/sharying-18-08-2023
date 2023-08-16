import { render } from '@testing-library/react'
import Card from '../components/card';
import { Status, convertStatusToString } from '../models/Task';

describe('IndexTask Component', () => {
    it('deve renderizar o título', () => {
        const { getByText } = render(<Card task={{
            id: "code",
            title: "Tarefas",
            description: "Tarefas do dia a dia",
            status: Status.NOT_STARTED
        }} />)

        expect(getByText('Tarefas')).toBeInTheDocument()
    });

    it('deve renderizar a descrição', () => {
        const { getByText } = render(<Card task={{
            id: "code",
            title: "Tarefas",
            description: "Tarefas do dia a dia",
            status: Status.NOT_STARTED
        }} />)

        expect(getByText('Tarefas do dia a dia')).toBeInTheDocument()
    });

    it('deve renderizar o status', () => {
        const { getByText } = render(<Card task={{
            id: "code",
            title: "Tarefas",
            description: "Tarefas do dia a dia",
            status: Status.NOT_STARTED
        }} />)

        expect(getByText('Não Iniciado')).toBeInTheDocument()
    });

    it('deve converter enum status para string', () => {
        const status = Status.NOT_STARTED

        expect(convertStatusToString(status)).toBe('Não Iniciado');
    });

})