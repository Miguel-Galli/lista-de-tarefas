# Lista de Tarefas

Aplicativo Android desenvolvido em Kotlin para gerenciar tarefas diárias com funcionalidades de adicionar, editar, listar e excluir tarefas.

---

## Tecnologias utilizadas

- Kotlin para desenvolvimento Android
- Android Studio (IDE recomendada)
- RecyclerView para exibição da lista de tarefas
- SQLite para persistência local (via classes TaskDAO e DataBaseHelper)
- ViewBinding para acesso seguro aos elementos da interface
- Design Material com FloatingActionButton para adicionar tarefas

---

## Funcionalidades

- Visualização da lista de tarefas salvas
- Adição de novas tarefas
- Edição de tarefas existentes
- Exclusão de tarefas com confirmação
- Persistência local dos dados usando SQLite

---

## Como usar

1. Clone o repositório para sua máquina local:
   ```
   git clone <URL-do-repositório>
   ```
2. Abra o projeto no Android Studio.
3. Conecte um dispositivo Android ou configure um emulador.
4. Compile e execute o aplicativo.
5. Na tela inicial, visualize as tarefas já cadastradas.
6. Para adicionar uma tarefa, clique no botão flutuante de adicionar.
7. Preencha a descrição da tarefa e salve.
8. Para editar uma tarefa, toque sobre ela na lista.
9. Para excluir, use a opção de exclusão na lista e confirme.

---

## Estrutura do projeto

- `MainActivity.kt` — Tela principal que lista as tarefas e permite editar/excluir.
- `AdicionarTarefaActivity.kt` — Tela para adicionar ou editar uma tarefa.
- `TaskAdapter.kt` — Adapter para exibir tarefas no RecyclerView.
- `TaskDAO.kt` e `DataBaseHelper.kt` — Classes para manipulação do banco SQLite.
- Layouts XML em `res/layout` definem as interfaces das telas.
- Recursos gráficos em `res/drawable` e `res/mipmap`.

---

## Contato

Para dúvidas ou sugestões, entre em contato.

---

*Projeto desenvolvido para aprendizado e prática de desenvolvimento Android.*
