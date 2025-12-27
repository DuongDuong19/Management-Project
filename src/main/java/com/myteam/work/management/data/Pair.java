package com.myteam.work.management.data;


public record Pair<F, S>(F first, S second) {}

record HashSetMap<F, S, T>(Pair<F, S> key, T third){}
