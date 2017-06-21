package org.hisp.dhis.system.cache;

/*
 * Copyright (c) 2004-2017, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.function.Function;
import javax.annotation.Nonnull;
import java.io.Serializable;

import java.util.Optional;

/**
 * {@code Caffeine} based cache implementation. Values are wrapped in an optional
 * in order to avoid caching null values directly which is not supported by
 * Caffeine.
 * 
 * @author Lars Helge Overland
 */
public class CaffeineCache<V extends Serializable>
    implements Cache<V>
{
    private com.github.benmanes.caffeine.cache.Cache<String, Optional<V>> cache;

    public CaffeineCache( com.github.benmanes.caffeine.cache.Cache<String, Optional<V>> cache )
    {
        this.cache = cache;
    }

    @Override
    public void put( String key, V value )
    {
        Optional<V> optValue = Optional.ofNullable( value );
        
        cache.put( key, optValue );
    }

    @Override
    public V getIfPresent( @Nonnull String key )
    {
        Optional<V> value = cache.getIfPresent( key );
        
        return value != null ? value.orElse( null ) : null;
    }
    
    @Override
    public V get( String key, Function<String, ? extends V> mappingFunction )
    {
        Function<String, Optional<V>> optMappingFunction = ( k ) -> Optional.ofNullable( mappingFunction.apply( k ) );
        
        Optional<V> value =  cache.get( key, optMappingFunction );
        
        return value.orElse( null );
    }

    @Override
    public void invalidate( String key )
    {
        cache.invalidate( key );        
    }

    @Override
    public void invalidateAll()
    {
        cache.invalidateAll();        
    }
}
