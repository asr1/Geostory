<?php
namespace GeoStory;
return array(
     'controllers' => array(
         'invokables' => array(
             'GeoStory\Controller\GeoStory' => 'GeoStory\Controller\GeoStoryController',

         ),
     ),
    // The following section is new and should be added to your file
    'router' => array(
        'routes' => array(
            'geostory' => array(
                'type'    => 'literal',
                'options' => array(
                    'route'    => '/geostory',
                    'defaults' => array(
                        'controller' => 'GeoStory\Controller\GeoStory',
                        'action'     => 'index',
                    ),
                ),
                'may_terminate' => true,
                'child_routes' => array(
                    'stories' => array(
                        'type'    => 'literal',
                        'options' => array(
                            'route'    => '/stories',
                            'defaults' => array(
                                'action'     => 'stories',
                            ),
                            'may_terminate' => true,
                        ),
                    ),
                    'json' => array(
                        'type'    => 'literal',
                        'options' => array(
                            'route'    => '/json',
                            'defaults' => array(
                                'action'     => 'json',
                            ),
                            'may_terminate' => true,
                        ),
                    ),
                    'echo' => array(
                        'type'    => 'literal',
                        'options' => array(
                            'route'    => '/echo',
                            'defaults' => array(
                                'action'     => 'echo',
                            ),
                            'may_terminate' => true,
                        ),
                    ),

                ),
            ),
        ),
    ),
    'service_manager' => array(
        'aliases' => array(
            'zfcuser_zend_db_adapter' => '  Zend\Db\Adapter\Adapter',
        ),
    ),


     'view_manager' => array(
         'display_not_found_reason' => true,
         'display_exceptions'       => true,
         'doctype'                  => 'HTML5',
         'not_found_template'       => 'error/404',
         'exception_template'       => 'error/index',
         'template_map' => array(
             'error/404'     => __DIR__ . '/../view/error/404.phtml',
             'error/index'   => __DIR__ . '/../view/error/index.phtml',
         ),
         'template_path_stack' => array(
             'geoStory' => __DIR__ . '/../view',
         ),
         'strategies' => array(
             'ViewJsonStrategy',
         ),
     ),
    // Doctrine config
    'doctrine' => array(
        'driver' => array(
            __NAMESPACE__ . '_driver' => array(
                'class' => 'Doctrine\ORM\Mapping\Driver\AnnotationDriver',
                'cache' => 'array',
                'paths' => array(__DIR__ . '/../src/' . __NAMESPACE__ . '/Entity')
            ),
            'orm_default' => array(
                'drivers' => array(
                    __NAMESPACE__ . '\Entity' => __NAMESPACE__ . '_driver'
                )
            ),
        ),
        'configuration' => array(
            'orm_default' => array(
                'numeric_functions' => array(),
                'datetime_functions' => array(),
                'string_functions'   => array('DISTANCE'  => 'GeoStory\Query\DistanceFunction'),
                'metadata_cache'     => 'filesystem',
                'query_cache'        => 'filesystem',
                'result_cache'       => 'filesystem',
            )
        )
    ),

    'controller_plugins' => array(
        'invokables' => array(
            'Params' => 'GeoStory\Controller\Plugin\Params',
            'UserAuthentication' => 'GeoStory\Controller\Plugin\UserAuthentication',
            'JsonGeoStoryPlugin' => 'GeoStory\Controller\Plugin\JsonGeoStoryPlugin',
        )
    ),
 );