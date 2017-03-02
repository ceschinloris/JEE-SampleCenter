# JEE-SampleCenter

Application web de gestion de samples

## Cahier des charges

### Genèse

En développant l'application web musicale Cadexmus ([github](https://github.com/HE-Arc/cadexmus2), [web](http://cadexmus.com)), nous avons eu besoin de fournir une interface de sélection de sons pour l'utilisateur. Nous avons remarqués que cette tâche était complexe et donc qu'elle pouvait faire l'objet d'un projet à part entière. L'idée est de créer une application externe qui peut être intégrée à Cadexmus et d'autres applications.

### Introduction

Pour faire de la musique, les compositeurs ont besoin d'échantillons de son (samples). Il existe déjà des plateformes web qui fournissent des samples (exemples [Freesound](https://www.freesound.org/), [SampleSwap](http://sampleswap.org), [Universal soundbank](http://www.universal-soundbank.com/)). Cependant, leur interface est souvent lourde, le référencement est basique et on ne peut pas l'utiliser depuis une autre application (API).

### But

Le but de la plateforme SampleCenter est de fournir une interface de gestions de samples. Les musiciens peuvent utiliser la plateforme pour trouver facilement le sample dont ils ont besoin et le télécharger. Ils pourraient aussi partager leurs propres samples et regrouper les samples qu'ils ont aimés.
Une autre utilisation de la plateforme serait en tant qu'API, c'est-à-dire depuis une autre application (web ou autre).

### Objectifs

#### Must-have

* Authentification
* Parcours de tous les samples dans une arborescence
* Recherche d'un sample (par nom, style et tags)
* Lecture d'un sample
* Création de sample et catégorisation (chemin, style et tags)
* Téléchargement d'un sample

#### Nice-to-have

* Fournir une API d'utilisation de SampleCenter
    * Une autre application qui a besoin de samples peut intégrer facilement les données de SampleCenter
    * Les données seraient retournées en JSON plutôt qu'en vues HTML
* Gestion des rôles
    * Un utilisateur invité peut lire et télécharger des samples
    * Un utilisateur inscrit peut créer des samples et les ranger dans des dossiers
    * Un admin peut créer des dossier et des styles et transformer un utilisateur inscrit en admin
* Recadrage temporel d'un sample
* Commentaires
* Like un sample
* Page de profil permettant de lister ses samples et ses likes
* Édition et suppression de ses samples (tous les samples pour les admin)
* Feed d'activité sur le profil utilisateur
* système de votes ( + 0 -)
* Système de privilèges aux utilisateurs avec un indice de confiance (pour garantir la bonne structure de l'arborescence)
    * Un utilsateur gagne des points si son action est validée par un utilisateur plus experimenté
    * Les actions des utilisateurs sont enregistrées pour permettre aux autres utilisateurs de les confirmer ou non
    
## Modélisation

![modélisation](https://raw.githubusercontent.com/ceschinloris/JEE-SampleCenter/master/schema_modelisation_bdd_sampleCenter.png)
