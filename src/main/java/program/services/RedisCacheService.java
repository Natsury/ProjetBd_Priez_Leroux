package program.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import program.entities.Recherche;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

// Le service contient toutes les méthodes en rapport avec la gestion de la base Redis
@Service        // Signaler service
public class RedisCacheService {
    private static final int REDIS_PORT = 6379;
    private static final int EXPIRATION_TIME_SECONDS = 3600; // 1 heure

    private Jedis jedis;

    public RedisCacheService() {
        this.jedis = new Jedis("localhost", REDIS_PORT);
    }

    public void sauvegarderRecherche(Recherche recherche) {
        try {
            // Convertir la recherche en json
            String jsonRecherche = convertObjectToJson(recherche);

            // Enregistrer la recherche avec le nom comme clé
            jedis.set(recherche.getNom(), jsonRecherche);

            // Expiration dans redis
            jedis.expire(recherche.getNom(), EXPIRATION_TIME_SECONDS);
        } catch (JedisException e) {
            throw new RuntimeException("Erreur Redis", e);
        }
    }

    public Recherche trouverRecherche(String nomRecherche) {
        try {
            // Récupérer la recherche en format json dans redis à partir de la clé
            String jsonRecherche = jedis.get(nomRecherche);

            if (jsonRecherche != null) {
                // Convertir le json en objet Recherche
                Recherche recherche = convertJsonToObject(jsonRecherche, Recherche.class);

                // Reset l'expiration dans redis
                if (recherche != null) {
                    jedis.expire(nomRecherche, EXPIRATION_TIME_SECONDS);
                }

                return recherche;
            } else {
                // Pas trouvé
                return null;
            }
        } catch (JedisException e) {
            throw new RuntimeException("Erreur Redis", e);
        }
    }

    private String convertObjectToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T convertJsonToObject(String json, Class<T> type) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PreDestroy         // Signaler méthode à exécuter à la l'arrêt de l'appli
    public void fermerConnexion() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
