(ns exercicios.exercicio
  (:require [exercicios.data :as data]))

(defn atualizar-endereco
  "Deve atualizar o endereco de um cliente e retorna o vetor de clientes.
   A funcao deve atualizar o endereco apenas com os valores presentes no mapa do novo endereco."
  [clientes id-cliente novo-endereco]
  (map
    (fn [item]
      (if (= id-cliente (:id item))
        (update-in
          item
          [:endereco]
          #(merge % novo-endereco)) item))
    clientes))

(comment
  (atualizar-endereco data/clients "5" {:rua "Alfonso"})
  (atualizar-endereco data/clients "3" {:estado "DF" :cidade "Brasília"})
  )

(defn- creditar-conta
  "adiciona valor ao saldo da conta"
  [conta valor]
  (update conta :saldo #(+ valor %)))

(comment
  (creditar-conta {:id-cliente "1"
                   :agencia    "0001"
                   :saldo      10000} 250)
  )

(defn- debitar-conta
  "remove valor do saldo da conta"
  [conta valor]
  (update conta :saldo #(- % valor)))

(comment
  (debitar-conta {:id-cliente "1"
                  :agencia    "0001"
                  :saldo      10000} 1000)
  )

(defn atualizar-conta
  "funcao que atualiza conta a partir de uma funcao f. Deve retornar o mapa de contas"
  [contas f conta valor])