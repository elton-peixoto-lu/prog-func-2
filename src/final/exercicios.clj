(ns final.exercicios
  (:require [final.data :as data]))


;; Criar funcao que, dado um vetor de clientes, deve atualizar o endereco de um cliente e retorna o vetor de clientes.
;; A funcao deve atualizar o endereco apenas com os valores presentes no mapa do novo endereco.
;; Para saber a posicao do cliente no vetor pode usar (.indexOf <vetor> <elemento>).

(defn busca-cliente
  [clientes id-cliente]
  (first (filter #(= id-cliente (:id %)) clientes)))

(defn alterar-endereco
  [cliente novo-endereco]
  (update cliente :endereco merge novo-endereco))

(defn atualiza-endereco
  [clientes id-cliente novo-endereco]
  (let [cliente (busca-cliente clientes id-cliente)
        index-cliente (.indexOf clientes cliente)
        cliente-alterado (alterar-endereco cliente novo-endereco)]
    (assoc clientes index-cliente cliente-alterado)))

;; Criar funcao que recebe o vetor de compras e retorna um mapa de compras,
;; onde a chave deve ser o id do cliente e o valor deve ser um vetor que contendo todas as compras do cliente.
(defn mapa-quantidades
  [compras]
  (reduce (fn [acc x] (update acc (:id-cliente x) #(conj (or % []) (dissoc x :id-cliente)) )) {} compras))

;; Criar funcao que filtra um vetor de clientes pela chave `:estado` do endereco.
(defn cliente-pertence-estado?
  [cliente estado]
  (-> cliente
      :endereco
      :estado
      (= estado)))
(defn clientes-por-estado
  [clientes estado]
  (filter #(cliente-pertence-estado? % estado ) clientes))

;; Criar funcao para retornar um mapa, tendo a chave do item como o id do produto e o valor sendo a quantidade total comprada desse item.
(defn mapa-quantidades
  [compras]
  (reduce (fn [acc x] (update acc (:id-item x) #(+ (:quantidade x) (or % 0)))) {} compras))


;; Dado 2 vetores de clientes (clientes e clientes-2), retornar clientes que est�o presentes em ambos os vetores.
(defn clientes-duplicados
  [v-cliente v-cliente-2]
  (filterv (set v-cliente) (set v-cliente-2)))

(defn clientes-duplicados-intersection
  [v-cliente v-cliente-2]
  (clojure.set/intersection (set v-cliente) (set v-cliente-2)))
