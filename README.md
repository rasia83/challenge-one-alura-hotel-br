# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="400" src="https://user-images.githubusercontent.com/101413385/173164615-192ca98a-1a44-480e-9229-9f82f456eec8.png">

</p>

---
<br />
<br />
<br />

---
## SQL / BD
#### repositório 
`<repositório hotel_alura_db.sql>` : <https://github.com/rasia83/challenge-one-alura-hotel-br/blob/081600324139eb4da9c77f0ecf409fe3135bd436/hotel_alura_db.sql>

#### todas as tabelas do banco seguiram este padrão: <br />
`id` para Primary Key gerada pelo banco. <br />
`active` campo boleando usado para indicar que um registro foi "apagado" do sistema. <br />
`create_time` campo populado pela trigger com a data de registro dos dados. <br />
`update_time` campo populado pela trigger com a data de alteração dos dados. <br />

<p>
     <img width="500" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/f20dc650a95709a1d740091a3789231db4f1ceb7/midia/SQL.jpg">
</p>
<br />

---
## login
[video](https://github.com/rasia83/challenge-one-alura-hotel-br/blob/35c17fbf90ecae4921069cadca59cfdfedd64ae6/midia/login.mp4)

 
 #### O password fica armazenado em formato hash(SHA-256)  e deve ser validado na tela inicial.
 
`script SQL` <br />

<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_login_04.jpg">
</p>
 <br />
 
`rasia.hotelalura.dao.LoginDAO` <br />
<p>
     <img width="620"  src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_login_05.jpg">
</p>
 <br />
 
`rasia.hotelalura.util.EncryptPassword` <br />
<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_login_06.jpg">
</p>

---
## registro_de_reserva
[video](https://github.com/rasia83/challenge-one-alura-hotel-br/blob/35c17fbf90ecae4921069cadca59cfdfedd64ae6/midia/registro_de_reserva.mp4)

#### No Registro de Reserva, a primeira tela grava os dados dá data de check-in e check-out o valor é calculado com base no valor das diárias obtido do banco de dados(daily_rate) e também registra a forma de pagamento gravando estas informações na tabela reservations.
#### Em sequência vem a tela de hóspedes(guests). Gravando nome, sobrenome, aniversário, nacionalidade, telefone e com qual reserva este hóspede está relacionado.

<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_cadastro_02.jpg">
</p>

<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_cadastro_03.jpg">
</p>

---
## buscar
[video](https://github.com/rasia83/challenge-one-alura-hotel-br/blob/35c17fbf90ecae4921069cadca59cfdfedd64ae6/midia/buscar.mp4)


<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_busca_02.jpg">
</p>
<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_busca_04.jpg">
</p>
 
`rasia.hotelalura.views.Buscar` <br />

<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_busca_04b.jpg">
</p>

`rasia.hotelalura.dao.GuestDAO` <br />

<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/74d104c21f244475a319a232796a0674cb9e2809/midia/hotelalura_busca_04c.jpg">
</p>

---
## editar
[video](https://github.com/rasia83/challenge-one-alura-hotel-br/blob/35c17fbf90ecae4921069cadca59cfdfedd64ae6/midia/editar.mp4)
#### A edição só é possível se houver algum registro selecionada e ela verifica qual a tab ativa, Registro de Reserva ou Hóspedes, o registro a ser editado é carregado na sua respectiva tela.

---
## excluir
[video](https://github.com/rasia83/challenge-one-alura-hotel-br/blob/35c17fbf90ecae4921069cadca59cfdfedd64ae6/midia/excluir.mp4)
#### A exclusão também só é possível se houver um registro selecionado. 
#### O sistema trada a exclusão de uma informação com a alteração de um campo boleano da tabela e não a exclusão definitiva da informação.


<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/da326185019d3ab917b05281ce3685f1a5d5ae0c/midia/hotelalura_excluir_01.jpg">
</p>
<p>
     <img width="620" src="https://github.com/rasia83/challenge-one-alura-hotel-br/blob/da326185019d3ab917b05281ce3685f1a5d5ae0c/midia/hotelalura_excluir_02.jpg">
</p>
