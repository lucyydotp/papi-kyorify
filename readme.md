## kyorify
A simple "wrapper" placeholder that converts legacy-formatted placeholders to MiniMessage-formatted
ones (referred to as Kyorifying). I originally created this for [ChatChat](https://github.com/HelpChat/ChatChat).

It's incredibly simple:
- chop off `kyorify_` prefix and parse it with PAPI
- replace & with § and deserialise using LegacyComponentSerializer
- serialize with MiniMessage

## Usage
Just put `kyorify` in front of any placeholder.
 For example, `%vault_prefix%` would become `%kyorify_vault_prefix%`.
