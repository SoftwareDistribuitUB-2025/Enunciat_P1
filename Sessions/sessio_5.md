# Sessió 5

En aquesta sessió realitzarem les proves creuades.

## Objectius

- Provar el funcionament del codi realitzat.
- Provar la compatibilitat entre diferents implementacions del mateix protocol.


## Fases de la sessió 

Per fer més àgil la sessió, s'ha definit un seguit de fases:

1. Preparació de la prova
2. Realització i documentació de les proves realitzades

**NOTA:** En aquesta sessió s'ha de redactar un informe de la prova, el qual s'haurà d'incloure en la memòria final. En aquest informe, s'evaluarà especialment les proves realitzades tant del pròpi codi com durant aquesta sessió, i no tant el resultat de les proves pel que fa al vostre codi, que s'avalua dins de la pràctica en si.

### Preparació de la prova

El primer pas per poder realitzar la prova creuada és tenir la informació de partida dels grups. Amb aquest objectiu, cal que identifiqueu la següent informació. Utilitzeu [aquest full de càlcul](https://ubarcelona-my.sharepoint.com/:x:/g/personal/xbaro_ub_edu/EUx51mJiYxVIlo_cO6dB8k0BKI-0Jh4Qpj_8s-1xRPIvWg?e=JwlsO8) a l'inici de la sessió per recollir aquesta informació:

- **Adreça IP** de la màquina on arrancareu el __Servidor__. Ho podeu fer visitant la següent pàgina web amb qualsevol navegador: https://www.whatismyip.com/
- **Estat real** de la vostra pràctica:
  - __Servidor__
    - [] El meu __Servidor__ arranca i permet que es connectin __Clients__, assignant-los a una partida.
    - [] El meu __Servidor__ té implementada la fase de configuració en que els __Clients__ afegeixen els vaixells al tauler.
    - [] El meu __Servidor__ implementa la dinàmica de joc, en la qual els __Clients__ van disparant en posicions per enfonsar els vaixells.
    - [] El meu __Servidor__ implementa el joc **multi-jugador**.
  - __Client__
    - [] El meu __Client__ es connecta correctament al servidor, afegint-se a una partida.
    - [] El meu __Client__ té implementada la fase de configuració en que l'usuari pot anar afegint els vaixells al tauler.
    - [] El meu __Client__ implementa la dinàmica de joc, en la qual l'usuari va indicant les posicions on disparar per enfonsar els vaixells de l'oponent.
  
A partir d'aquesta informació, s'organitzaran les proves creuades. A cada grup se li assignarà un mínim de dos grups per provar. Segons l'estat de la pràctica dels grups, podem tenir 3 escenàris:

- El grup té una implementació igual o més avançada que la nostra: Provem la part que tenim implementada.
- El grup té una implementació menys avançada que la nostra: Provem la part que el grup té implementada.
- El grup no té una part funcional prou desenvo

### Documentació de les proves realitzades

Per poder iniciar la prova creuada, us demanerm que creeu un nou fitxer **a l'arrel del vostre repositori** amb el nom `TestReport.md`, en el qual informareu de l'estat de la vostra prova i de les proves realitzades. Podeu utilitzar com a plantilla el fitxer [TestReport.md](../template/TestReport.md).

En aquest fitxer trobareu els apartats a omplir. Cal que el **Pull Request** d'aquesta setmana inclogui aquest fitxer amb els canvis que hagueu realitzat derivats de la sessió de proves.

### Treball fora del laboratori:

Finalitzar la documentació de les proves pròpies i acabar l'escriptura de la informació de les proves creuades.
