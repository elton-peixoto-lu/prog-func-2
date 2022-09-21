(ns aula3.exercicio
  (:require [aula3.data :as data]))

;; escrever uma funcao que retorna a primeira compra do cliente dado o ID.

;; escrever uma funcao que retorna as compras de um cliente dado o ID.

;; escrever uma funcao que adiciona a compra {:item "mesa" :valor 250} no vetor de compras de um cliente, dado o ID. Deve retornar o mapa inteiro.

;; escrever uma funcao que da desconto de 10% para o primeiro item de compra do cliente dado o ID. Deve retornar o mapa inteiro
;; escrever uma funcao que retorna a primeira compra do cliente dado o ID.

(defn get-first-shop-item
  "Get the first shop item by ID."
  [{:keys [compras]} id]
  (get (compras id) 0))

(comment
  (get-first-shop-item data/mapa "0")
  (get-first-shop-item data/mapa "2")
  (get-first-shop-item data/mapa "1")
  )

;; escrever uma funcao que retorna as compras de um cliente dado o ID.

(defn get-client-shops
  "Get all the shops from a client by ID."
  [{:keys [compras]} id]
  (compras id))

(comment
  (get-client-shops data/mapa "0")
  (get-client-shops data/mapa "3")
  (get-client-shops data/mapa "4")
  )

;; escrever uma funcao que adiciona a compra {:item "mesa" :valor 250} no vetor de compras de um cliente, dado o ID. Deve retornar o mapa inteiro.

(defn add-new-shop-item
  "Add a new shop item in a shops list by ID."
  [{:keys [compras] :as data} id new-shop]
  (assoc-in data [:compras id] (conj (compras id) new-shop)))

(comment
  (add-new-shop-item data/mapa "1" {:item "mesa" :valor 250})
  )

;; escrever uma funcao que da desconto de 10% para o primeiro item de compra do cliente dado o ID. Deve retornar o mapa inteiro

(defn add-discount-first-shop-item
  "Add a 10% discount for the first shop item by the client ID."
  [data id]
  (update-in data [:compras id 0 :valor] #(* % 0.9)))

(comment
  (add-discount-first-shop-item data/mapa "2")
  (get-first-shop-item data/mapa "1")
  )