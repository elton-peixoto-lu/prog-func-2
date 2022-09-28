(ns aula5.exercicio
  (:require [aula4.data :as dados]))


;; Utilizar loop/recur para resolver os exercicios


;; retornar o vetor de clientes a chave `:full-name` adicionada nos mapas de clientes. (full-name deve ser a concatenacao do `:first-name` com o `:last-name`).
;; Utilizar loop/recur para resolver os exercicios
(defn add-client-fullname
  [cliente]
  (assoc cliente
    :full-name
    (str (:first-name cliente) " " (:last-name cliente)))
  )

;; retornar o vetor de clientes a chave `:full-name` adicionada nos mapas de clientes. (full-name deve ser a concatenacao do `:first-name` com o `:last-name`).
(defn return-consumers-fullname
  [clients]
  (loop [clientes clients
         acc []]
    (if (empty? clientes)
      acc
      (recur (rest clientes)
             (conj acc (add-client-fullname (first clients))))
      )))

(comment
  (add-client-fullname (first dados/clients))
  (return-consumers-fullname dados/clients )
  )

;; retornar um mapa de compras, onde a chave deve ser o id do cliente e o valor deve ser um vetor que contendo todas as compras do cliente.


;; retornar o vetor de clientes com a chave `:purchases`. O valor dessa chave sera um vetor contendo mapas com `:item-id` e `:amount`.


;; escrever uma funcao que tras a frequencia de cada caracter em uma string.
