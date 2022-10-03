(ns aula9.exercicio
  (:require [aula9.dados-banco :as db]))

;; 1 crie um átomo com o vetor dados banco.
(def atom-dados-banco (atom db/banco-db))

;; 2 crie uma função capaz de trazer um cliente de acordo com o seu id
(defn get-client-by-id
  ""
  [clients-vector client-id]
  (some (fn [client]
          (when (= (:id client) client-id)
            client)) clients-vector))

(comment
  (get-client-by-id @atom-dados-banco 1))

(defn- update-client-balance!
  ""
  [clients-vector client-id value f]
  (let [current-client (get-client-by-id @clients-vector client-id)
        current-balance (:saldo current-client)
        new-client (update current-client :saldo f value)]
    (println "Saldo anterior:" current-balance "\nSaldo atual" (:saldo new-client))
    (swap! clients-vector (fn [coll]
                            (map (fn [client]
                                   (if (= (:id client) client-id)
                                     new-client
                                     client)) coll)))))

;; 3 crie uma função que, dado um valor adiciona esse valor do saldo existente de um cliente dado id.
;; A função deve printar o valor anterior do saldo
(defn add-value-to-client-balance!
  ""
  [clients-vector client-id value]
  (update-client-balance! clients-vector client-id value +))

(comment
  (add-value-to-client-balance! atom-dados-banco 1 10))

;; 4 crie uma função que, dado um valor subtrai esse valor do saldo existente de um cliente dado id.
;; A função deve printar o valor anterior do saldo
(defn sub-value-to-client-balance!
  ""
  [clients-vector client-id value]
  (update-client-balance! clients-vector client-id value -))

(comment
  (sub-value-to-client-balance! atom-dados-banco 1 10))

;; 5 crie uma função chamada 'transação' da qual você passa uma das operações (deposito/saque),
;; o valor a ser depositado/retirado e efetua uma transação bancária.

(defn transaction
  [clients-vector client-id value operation]
  (case operation
    :deposito (add-value-to-client-balance! clients-vector client-id value)
    :saque (sub-value-to-client-balance! clients-vector client-id value)))

(comment
  (transaction atom-dados-banco 1 10 :deposito)
  (transaction atom-dados-banco 1 10 :saque))