#include "config.h"
#include <EventLogging.hpp>
#include <settings/Bool.hpp>
#include <helpers.hpp>
#if ENABLE_VISUALS
#include <colors.hpp>
#include <init.hpp>

static settings::Bool enable{ "chat.log-events", "false" };

static void handlePlayerConnectClient(KeyValues *kv)
{
    PrintChat("\x07%06X%s\x01 \x07%06X%s\x01 joining", 0xa06ba0, kv->GetString("name"), 0x914e65, kv->GetString("networkid"));
}

static void handlePlayerActivate(KeyValues *kv)
{
    int uid    = kv->GetInt("userid");
    int entity = g_IEngine->GetPlayerForUserID(uid);
    player_info_s info{};
    if (g_IEngine->GetPlayerInfo(entity, &info))
        PrintChat("\x07%06X%s\x01 connected", 0xa06ba0, info.name);
}

static void handlePlayerDisconnect(KeyValues *kv)
{
    CachedEntity *player = ENTITY(g_IEngine->GetPlayerForUserID(kv->GetInt("userid")));
    if (player == nullptr)
        return;
    PrintChat("\x07%06X%s\x01 \x07%06X%s\x01 disconnected", colors::chat::team(player->m_iTeam()), kv->GetString("name"), 0x914e65, kv->GetString("networkid"));
}

static void handlePlayerTeam(KeyValues *kv)
{
    if (kv->GetBool("disconnect"))
        return;

    int oteam           = kv->GetInt("oldteam");
    int nteam           = kv->GetInt("team");
    const char *oteam_s = teamname(oteam);
    const char *nteam_s = teamname(nteam);
    PrintChat("\x07%06X%s\x01 changed team (\x07%06X%s\x01 -> "
              "\x07%06X%s\x01)",
              0xa06ba0, kv->GetString("name"), colors::chat::team(oteam), oteam_s, colors::chat::team(nteam), nteam_s);
}

static void handlePlayerHurt(KeyValues *kv)
{
    int victim   = kv->GetInt("userid");
    int attacker = kv->GetInt("attacker");
    int health   = kv->GetInt("health");
    player_info_s kinfo{};
    player_info_s vinfo{};
    g_IEngine->GetPlayerInfo(g_IEngine->GetPlayerForUserID(victim), &vinfo);
    g_IEngine->GetPlayerInfo(g_IEngine->GetPlayerForUserID(attacker), &kinfo);
    CachedEntity *vic = ENTITY(g_IEngine->GetPlayerForUserID(victim));
    CachedEntity *att = ENTITY(g_IEngine->GetPlayerForUserID(attacker));

    if (vic == nullptr || att == nullptr)
        return;

    PrintChat("\x07%06X%s\x01 hurt \x07%06X%s\x01 down to \x07%06X%d\x01hp", colors::chat::team(att->m_iTeam()), kinfo.name, colors::chat::team(vic->m_iTeam()), vinfo.name, 0x2aaf18, health);
}

static void handlePlayerDeath(KeyValues *kv)
{
    int victim   = kv->GetInt("userid");
    int attacker = kv->GetInt("attacker");
    player_info_s kinfo{};
    player_info_s vinfo{};
    g_IEngine->GetPlayerInfo(g_IEngine->GetPlayerForUserID(victim), &vinfo);
    g_IEngine->GetPlayerInfo(g_IEngine->GetPlayerForUserID(attacker), &kinfo);
    CachedEntity *vic = ENTITY(g_IEngine->GetPlayerForUserID(victim));
    CachedEntity *att = ENTITY(g_IEngine->GetPlayerForUserID(attacker));

    if (vic == nullptr || att == nullptr)
        return;

    PrintChat("\x07%06X%s\x01 killed \x07%06X%s\x01", colors::chat::team(att->m_iTeam()), kinfo.name, colors::chat::team(vic->m_iTeam()), vinfo.name);
}

static void handlePlayerSpawn(KeyValues *kv)
{

InitRoutine init([]() { g_IGameEventManager->AddListener(&listener, false); });

bool event_logging::isEnabled()
{
    return *enable;
}
#endif