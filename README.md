# MowItNow -- Projet Scala 2021/2022

Projet réalisé par Wendy Alejandra Corredor Morales dans le cadre du Master 2 IMSD. 

## Idée générale.

La société MowItNow a décidé de développer une tondeuse à gazon automatique,
destinée aux surfaces rectangulaires.
La tondeuse peut être programmée pour parcourir l'intégralité de la surface. La position de
la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une lettre
indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est
divisée en grille pour simplifier la navigation.

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se
situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.
Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres
possibles sont « D », « G » et « A ».
« D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la
déplacer. « A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle
elle fait face, et sans modifier son orientation.

Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit :
• La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse,
celles du coin inférieur gauche sont supposées être (0,0).
• La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées.
Chaque tondeuse a deux lignes la concernant :
- La première ligne donne la position initiale de la tondeuse, ainsi que son
orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres
et une lettre, séparés par un espace.
- La seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer
la pelouse. Les instructions sont une suite de caractères sans espaces.

### OBJECTIF
Concevoir et écrire un programme en Scala, implémentant la spécification ci-dessus et
passant le test ci-après.


### TEST
Le fichier suivant est fourni en entrée :

5 5

1 2 N

GAGAGAGAA

3 3 E

AADAADADDA

### Résultat attendu

On attend le résultat suivant (position finale des tondeuses) :


Tondeuse 1 : 1 3 N

Tondeuse 2 : 5 1 E


## Fichiers 
input.txt contient l'input qui doit être 
game.scala contient la logique du logiciel et utilise input.txt pour son exécution. 


## Comment exécuter.
Ouvrir depuis IntelliJ et exécuter. 
