(ns aula4.exercicio
  (:require [aula4.data :as data]))


;; retornar o vetor de clientes a chave `:full-name` adicionada nos mapas de clientes. (full-name deve ser a concatenacao do `:first-name` com o `:last-name`).
(defn full-name
  [client]
  (let [{:keys [first-name last-name]} client]
    (str first-name " " last-name)))

(defn add-full-name-keyword
  [client]
  (assoc client :full-name (full-name client)))

(defn add-clients-full-name
  [clients]
  (map add-full-name-keyword clients))

(comment
  (add-clients-full-name data/clients))

;; retornar clientes que tem `:age` maior que 35. Usar tanto `filter` quanto `remove`.
(defn get-older-clients
  [clients]
  (filter #(> (:age %) 35) clients))

(comment
  (get-older-clients data/clients)
  )

;; retornar um mapa, tendo a chave do item como o id do produto e o valor sendo a quantidade total comprada desse item.
(defn sum-amounts
  [mapa item]
  (let [{id :item-id amount :amount} item
        current-amount (get mapa id 0)]
    (assoc mapa id (+ current-amount amount))))

(defn purchases-by-products
  [purchases]
  (reduce sum-amounts {} purchases))

(comment
  (purchases-by-products data/purchases)

  ;; TESTES
  (assoc mapa item-id (+ amount current-amount))
  (sum-amounts {"1" 20 "2" 3} {:item-id "4" :amount 10})
  (sum-amounts {"1" 20 "2" 3} {:item-id "1" :amount 10})
  )

;; retornar um mapa de compras, onde a chave deve ser o id do cliente e o valor deve ser um vetor que contendo todas as compras do cliente.

(defn get-client-purchases
  [mapa purchase]
  (let [{id :client-id} purchase
        client-purchases (get id mapa [])]
    (->> (dissoc purchase :client-id)
         (conj client-purchases)
         (assoc mapa id))))

(defn purchases-by-clients
  [clients]
  (->> clients
       (reduce get-client-purchases {})))

(comment
  (purchases-by-clients data/purchases)
  )