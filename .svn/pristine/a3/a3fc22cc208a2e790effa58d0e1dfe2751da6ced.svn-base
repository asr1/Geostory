<?php
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 10/2/2014
 * Time: 1:13 AM
 */
namespace GeoStory\Controller\Plugin;

use Zend\Mvc\Controller\Plugin\Params as ZendParams;

class Params extends ZendParams {

    public function fromJson() {
        $body = $this->getController()->getRequest()->getContent();
        if (!empty($body)) {
            $json = json_decode($body, true);
            if (!empty($json)) {
                return $json;
            }
        }
        return false;
    }

}