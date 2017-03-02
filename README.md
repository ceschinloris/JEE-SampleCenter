# JEE-SampleCenter

Application web de gestion de samples

## Cahier des charges

### Genèse

En développant l'application web musicale Cadexmus ([github](https://github.com/HE-Arc/cadexmus2), [web](http://cadexmus.com)), nous avons eu besoin de fournir une interface de sélection de sons pour l'utilisateur. Nous avons remarqués que cette tâche complexe pouvait faire l'objet d'un projet à part entière.

### Introduction

Pour faire de la musique, les compositeurs ont besoin d'échantillons de son (samples). Il existe déjà des plateformes web qui fournissent des samples (exemples [Freesound](https://www.freesound.org/), [SampleSwap](http://sampleswap.org), [Universal soundbank](http://www.universal-soundbank.com/)). Cependant, leur interface est souvent lourde, le référencement est basique et on ne peut pas l'utiliser depuis une autre application (API).

### But

Le but de la plateforme SampleCenter est de fournir une interface de gestions de samples. Les musiciens peuvent utiliser la plateforme pour trouver facilement le sample dont ils ont besoin et le télécharger. Ils pourraient aussi partager leurs propres samples et regrouper les samples qu'ils ont aimés.
Une autre utilisation de la plateforme serait en tant qu'API, c'est-à-dire depuis une autre application (web ou autre).

### Objectifs

#### Must-have

* Authentification
* Création de sample et catégorisation (chemin, style et tags)
* Édition des informations d'un sample 
* Parcours de tous les samples dans une arborescence
* Recherche d'un sample (par nom, style et tags)
* Lecture d'un sample
* Téléchargement d'un sample
* Gestion des rôles
    * Un utilisateur invité peut lire et télécharger des samples
    * Un utilisateur inscrit peut créer des samples, mais pas des dossiers
    * Un admin peut créer des dossier et transformer un utilisateur inscrit en admin

#### Nice-to-have

* Recadrage d'un sample
* Fournir une API d'utilisation de SampleCenter
    * Une autre application qui a besoin de sample peut intégrer facilement les données de SampleCenter
    * Les données seraient retournées en JSON plutôt qu'en vues HTML
    * Ne concerne que la recherche et la lecture de samples
* Commentaires
* Like un sample
* Lister ses likes
* Feed d'activité sur un profil utilisateur
* système de votes ( + 0 -)
* Système de privilège aux utilisateurs avec un indice de confiance (pour garantir la bonne structure de l'arborescence)
    * Un utilsateur gagne des points si son action est validée par un utilisateur plus experimenté
